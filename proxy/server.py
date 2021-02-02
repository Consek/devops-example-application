import tornado.ioloop
import tornado.web
from tornado.httpclient import AsyncHTTPClient
from tornado import gen
import socket
import requests
import atexit
import os

target_host = os.getenv('TARGET_HOST', 'localhost')
target_port = os.getenv('TARGET_PORT', '8080')


class MainHandler(tornado.web.RequestHandler):

    @gen.coroutine
    def get(self):
        resp = yield AsyncHTTPClient().fetch("http://{}:{}/instances".format(target_host, target_port))
        self.set_status(resp.code)
        self.add_header('Content-Type', 'application/json')
        self.write(resp.body)

def register():
    hostname = socket.gethostname()
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
    print("Exit Python application")

if __name__ == "__main__":
    atexit.register(cleanup)
    register()
    application = tornado.web.Application([
        (r"/instances", MainHandler),
    ])
    application.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
