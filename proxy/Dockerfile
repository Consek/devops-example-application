FROM python:3.9.1-slim
ADD ./ ./
RUN pip install -r requirements.txt

CMD ["python3", "server.py"]
EXPOSE 8888
