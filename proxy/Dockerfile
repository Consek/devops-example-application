FROM python:3.9.1-slim
ADD requirements.txt ./requirements.txt
RUN pip install -r requirements.txt

ADD server.py ./server.py
CMD ["python3", "server.py"]
EXPOSE 8888
