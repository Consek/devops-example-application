import tornado.ioloop
import tornado.web
from tornado.httpclient import AsyncHTTPClient
from tornado import gen
import socket
import requests
import atexit
import os
import json
import signal
import time

target_host = os.getenv('TARGET_HOST', 'localhost')
target_port = os.getenv('TARGET_PORT', '8080')
hostname = socket.gethostname()

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
        resp = yield AsyncHTTPClient().fetch("http://{}:{}/instances".format(target_host, target_port))
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
    response = requests.post('http://{}:{}/instance'.format(target_host, target_port), json=post_data)
    if response.status_code == 200:
        print("Registered current instance")
    else:
        print("Error registering instance")
        exit(1)

def cleanup():
    print("Removing myself form cache")
    requests.delete('http://{}:{}/instance/{}'.format(target_host, target_port, hostname))
    print("Exit Python application")

if __name__ == "__main__":
    signal.signal(signal.SIGTERM, cleanup)
    atexit.register(cleanup)
    register()
    application = tornado.web.Application([
        (r"/instances", MainHandler),
    ])
    application.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
