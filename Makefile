build/docker:
	$(info building docker image)
	@ docker image build --buiild-arg SKIP_TEST --build-arg DATABASE_URL -t workshop-devops .