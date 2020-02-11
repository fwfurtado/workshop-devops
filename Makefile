build/docker:
	$(info building docker image)
	@ docker image build --build-arg SKIP_TEST --build-arg DATABASE_URL -t workshop-devops .


tag/docker:
	$(info tagging docker image)
	@ docker image tag workshop-devops $(tag)