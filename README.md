# simple-irc-grpc

Simple IRC implemented on top of grpc and protobuf

## Setup

This section is to help you guys get out from frustration of setting up Protobuf and Grpc. The steps are intended to Java users only, but others may follow similar procedures.

### Protobuf Setup

**You have to use the pre-release v3.0.0 (any of them, but the latest is recommended) in order to use Grpc along with Protobuf.** To verify it, make sure that the output of `protoc --version` is showing `libprotoc 3.0.0` after you install the protobuf.

#### Windows Users

Good news for Windows users, there is already a binary release for Windows. You can find it in [https://github.com/google/protobuf/releases](https://github.com/google/protobuf/releases). Remember to grab the v3.0.0 (mine was v3.0.0-alpha-2).

#### Linux Users

The following steps are only tested on Ubuntu 14.04 32-bit. I do not guarantee success on other platform (I do not even guarantee it on the same machine).

Clone the repository from github:

	git clone https://github.com/google/protobuf.git

Or you can grab it from [https://github.com/google/protobuf/releases](https://github.com/google/protobuf/releases).

After this, internet connection will be required. I strongly suggest to use non-proxy connection to download the dependencies, as there will only be few MBs of required data.

	cd protobuf
	./autogen.sh
	./configure
	make
	sudo make install

Along the way, you may see some failing tasks, because you don't have the required tools (`autoreconf` and `libtoolize`). You can get it by running `sudo apt-get install autoreconf` and `sudo apt-get install libtoolize`.

After this, you will find executable `protoc` inside the `src` directory. Verify everything works well by executing the following commands:

	cd src
	./protoc

You will see `Missing input file`. If you want to use protoc from other directories, copy the executable and the libs by executing:

	sudo cp protoc /usr/local/bin
	sudo cp -avr .libs/ /usr/local/bin


Again, to verify it, navigate to any other directory and run `protoc` (without `./`). You will see `Missing input file` if everything went well.

### Grpc Setup

This setup is only for grpc-java. Other languages are not tested yet, but C-based languages (C++ and C# included) are expected to be easier.

#### Windows Users

Bad news, Windows 32-bit is not supported by grpc-java by now. [This page](https://github.com/grpc/grpc-java/tree/master/compiler) says that Windows 64-bit can build it if you have MSYS2 installed, although it was not tested yet.

#### Linux Users

Again, this is only tested using Linux 14.04 32-bit. I tried to build stuff directly from the github source by cloning it, but I got errors regarding libtools. So I got the source code from the releases ([https://github.com/grpc/grpc-java/releases](https://github.com/grpc/grpc-java/releases), specifically v0.8.0) and the build works just fine.

So, download the .tar.gz file and extract the contents of it. Execute:

	cd grpc-java-0.8.0/compiler
	../gradlew java_pluginExecutable

That's it. The output will appear in `grpc-java-0.8.0/compiler/build/binaries/java_pluginExecutable/`, an executable called `protoc-gen-grpc-java`.

If you got errors in the building process, you may need to check the Java version that you have. Building requires Java 8. Run `java -version` to figure it out. Installing Java 8 may a little bit tricky, because running `sudo apt-get install openjdk-8-jre` will fail because it is not supported yet in 14.04 (but 14.10 may do). You will need to get it from another source.

	sudo add-apt-repository ppa:webupd8team/java -y
	sudo apt-get update
	sudo apt-get install oracle-java8-installer
	sudo apt-get install oracle-java8-set-default

### Generating Java files

This is taken from the example files from `grpc-java`. Save the following to `hello_world.proto`.

	syntax = "proto3";

	package helloworld;

	option java_multiple_files = true;
	option java_package = "io.grpc.examples.helloworld";
	option java_outer_classname = "HelloWorldProto";

	service Greeter {
	  rpc SayHello (HelloRequest) returns (HelloResponse) {}
	}

	message HelloRequest {
	  string name = 1;
	}

	message HelloResponse {
	  string message = 1;
	}

Now, to generate the java files, run the following command.

	protoc --plugin=protoc-gen-grpc-java=location/to/protoc-gen-grpc-java --grpc-java_out=. --java_out=. hello_world.proto

where `location/to/protoc-gen-grpc-java` is the location of build result above (by default, it is in `grpc-java-0.8.0/compiler/build/binaries/java_pluginExecutable/protoc-gen-grpc-java`. You will see new files called `GreeterGrpc.java` and `HelloWorldProto.java` in folder `io/grpc/examples/helloworld`. `GreeterGrpc.java` contains the generated client stubs and server interfaces. `HelloWorldProto.java` contains the "Java version" of `HelloRequest` and `HelloResponse`.

### Getting errors?

When you compile your own proto files, you may see errors like `expected messsage type` or similar. That is because all of data types in the service definition must be a message. It cannot have a primitive types like `string` or `int32`. Also, you must have exactly one message in input or output of each methods. For instance, all of the service definition below are not allowed.

	service Service {
	  rpc getSomething (int32) returns (string) {} // primitive types are not allowed
	  rpc getSomethingElse (Message1, Message2) returns (Message3) {} // multiple inputs are not allowed
	  rpc getSomethingElse () returns (Message) {} // no input is also not allowed
	}

An (ugly) workaround is to declare a message of required types like:

	message MyInt32 {
	  int32 value = 1;
	}

and use `MyInt32` as `int32` in your services.

Luckily you can have a list of messages as input or output, by declaring `stream` in front of the message, like this

	service Service {
	  rpc getSomething (stream Message1) returns (stream Message2) {}
	}

For more information, refer to the official documentation [http://www.grpc.io/docs/tutorials/basic/java.html](http://www.grpc.io/docs/tutorials/basic/java.html) (for Java users).

### Using the files in your project

Just import the java files to your project. Notice that those files won't compile because you are missing some dependencies. The required ones are:

- `grpc-all-0.8.0.jar`. The latest version is 0.9.0, but I pick 0.8.0 because I use grpc of the same version.
- `protobuf-java-*.jar`. Just grab the latest version.
- `guava-*.jar`. Also grab the latest version.

You can find them in the [Maven Central Repository](http://search.maven.org/). Or just grab it from the [lib](https://github.com/azaky/simple-irc-grpc/tree/master/lib) folder in this repository.

After adding those jars to your project, the java files should compile just fine. Then you may proceed to [http://www.grpc.io/docs/tutorials/basic/java.html](http://www.grpc.io/docs/tutorials/basic/java.html) for more information about implementing client and servers.

But wait, if you start your server now, it will crash because it is *still* missing some dependencies, due to class loaders. The required dependencies to make it run are:

- `hpack-0.11.0.jar`.
- `netty-all-4.1.0.Beta5.jar`. *Pick this exact same version*. I tried using Beta6 or 5.0.0 and it will break because of backwards incompatibility.

Now everything should be fine.

### That's it, Good Luck!
