import requests

frontend_url = "http://localhost:8000/"
proxy_root_url = "http://localhost:8888/"
proxy_instances_url = "{}instances".format(proxy_root_url)

def test_if_proxy_returns_404_on_not_root():
  response = requests.get("http://localhost:8888/")
  assert response.status_code == 404

def test_if_proxy_instances_returns_200():
  response = requests.get(proxy_instances_url)
  assert response.status_code == 200

def test_if_proxy_instances_content_type_is_application_json():
  response = requests.get(proxy_instances_url)
  assert response.headers["Content-Type"] == "application/json"

def test_if_all_fields_are_defined():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  for instance in response_body:
    assert {"hostname", "version", "isProxy", "isActive"} == instance.keys()

def test_number_of_proxy_instances():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isProxy"] == True, response_body))
  assert len(instance_list) == 4
  

def test_number_of_backend_instances():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isProxy"] == False, response_body))
  assert len(instance_list) == 5

def get_hostnames():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  return [instance["hostname"] for instance in response_body]

def test_instance_hostnames_are_stable():
  initial_set = get_hostnames()
  for _ in range(1, 21):
    assert get_hostnames() == initial_set

def test_only_two_instances_are_active():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isActive"] == True, response_body))
  assert len(instance_list) == 2

def test_only_one_proxy_is_active():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isActive"] == True and 
    instance["isProxy"] == True, response_body))
  assert len(instance_list) == 1

def test_only_one_backend_is_active():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isActive"] == True and 
    instance["isProxy"] == False, response_body))
  assert len(instance_list) == 1

def test_only_one_backend_is_active():
  response = requests.get(proxy_instances_url)
  response_body = response.json()
  instance_list = list(filter(lambda instance: instance["isActive"] == True and 
    instance["isProxy"] == False, response_body))
  assert len(instance_list) == 1

def test_active_proxy_changes():
  instance_hostnames = set()
  for _ in range(1, 51):
    response = requests.get(proxy_instances_url)
    response_body = response.json()
    instance = next(filter(lambda instance: instance["isActive"] == True and 
      instance["isProxy"] == False, response_body))
    instance_hostnames.add(instance["hostname"])
  assert len(instance_hostnames) > 1

def test_if_frontend_returns_200():
  response = requests.get(frontend_url)
  assert response.status_code == 200
