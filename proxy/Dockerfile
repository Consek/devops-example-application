FROM python:3.9.1-slim

COPY requirements.txt ./requirements.txt
RUN pip install -r requirements.txt

COPY server.py ./server.py
RUN addgroup python && adduser --no-create-home --disabled-password --ingroup python python
USER python

CMD ["python3", "server.py"]
EXPOSE 8888
