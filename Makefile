
build/docker:
	$(info building docker image)
	@ docker image build --build-arg SKIP_TEST --build-arg DATABASE_URL -t workshop-devops .

login/docker:
	$(info sign-in with docker registry)
	@ docker login --username=_ --password=$$HEROKU_AUTH_TOKEN registry.heroku.com


tag/docker: build/docker login/docker
	$(info tagging docker image)
	@ docker image tag workshop-devops registry.heroku.com/workshop-devops/web

deploy: tag/docker
	$(info deploying via docker)
	@ docker push registry.heroku.com/workshop-devops/web
	@ make _release/heroku

_release/heroku:
	$(info sending to heroku)
	@ curl -X PATCH \
	-d '{"updates": [{"type": "web", "docker_image": "$(shell docker image inspect registry.heroku.com/workshop-devops/web --format={{.Id}} |  sed -r s/sha256://g )"}]}' \
	-H 'Content-Type: application/json' \
	-H 'Accept: application/vnd.heroku+json; version=3.docker-releases' \
	https://api.heroku.com/apps/workshop-devops/formation