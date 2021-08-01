all: compile

exec:
	java -cp out/ main.Application

compile:
	javac src/main/infra/exception/*.java src/main/domain/*.java src/main/Application.java -d out/