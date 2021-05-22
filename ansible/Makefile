run_ansible: generate_jenkins_password
	ansible-playbook playbook.yml
prepare: install_requrements install_python_requirements
install_requrements:
	ansible-galaxy install -r requirements.yml
install_python_requirements:
	pip3 install hcloud
generate_jenkins_password:
	@if ! [ -f jenkins_password ]; then openssl rand -base64 32 | tr -d '\n\r' > jenkins_password; fi
