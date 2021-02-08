import tornado.ioloop
import tornado.web
from tornado.httpclient import AsyncHTTPClient
from tornado import gen
from healthcheck import TornadoHandler, HealthCheck
import socket
import requests
import atexit
import os
import json
import signal
import time
import uuid

target_url = os.getenv('TARGET_URL', 'http://localhost:8080')
hostname = uuid.uuid4().hex if socket.gethostname() == 'localhost' else socket.gethostname()

def mark_me_active(body):
    body_json = json.loads(body)
    return_json = []
    for x in body_json:
        if x["hostname"] == hostname:
            x["isActive"] = True
        return_json.append(x)
    return json.dumps(body_json)


class MainHandler(tornado.web.RequestHandler):

    @gen.coroutine
    def get(self):
        resp = yield AsyncHTTPClient().fetch("{}/instances".format(target_url))
        self.set_status(resp.code)
        self.write(mark_me_active(resp.body))
        self.clear_header('Content-Type')
        self.add_header('Content-Type', 'application/json')
        self.add_header('Access-Control-Allow-Origin', '*')

def register():
    post_data = {
        "hostname": hostname,
        "version": "v1",
        "active": False,
        "proxy": True
    }
    response = requests.post('{}/instance'.format(target_url), json=post_data)
    if response.status_code == 200:
        print("Registered current instance")
    else:
        print("Error registering instance")
        exit(1)

def cleanup():
    print("Removing myself form cache")
    requests.delete('{}/instance/{}'.format(target_url, hostname))
    print("Exit Python application")

def is_ok():
    return True, "application ok"

health = HealthCheck(checkers=[is_ok])

if __name__ == "__main__":
    signal.signal(signal.SIGTERM, cleanup)
    atexit.register(cleanup)
    register()
    application = tornado.web.Application([
        ("/instances", MainHandler),
        ("/healthcheck", TornadoHandler, dict(checker=health))
    ])
    application.listen(os.getenv('PORT', '8888'))
    tornado.ioloop.IOLoop.instance().start()
