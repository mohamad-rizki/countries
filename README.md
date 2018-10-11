Countries App
==============

https://travis-ci.com/mohamad-rizki/countries.svg?branch=master

Android Application for example of unit test & code coverage with minimal config. Build with Java Language

## Getting Start

You can open this project with Android Studio

## Prerequisites

What things you need to install the software and install them :

* Java 8
* Gradle

If you using Mac OS, you could install this with [Homebrew](homebrew.sh)

```sh
$ brew cas install java8
$ brew install gradle
```

## Installing

Create first folder repo, then clone project

```sh
$ cd Workspace/android/
$ git clone https://github.com/mohamad-rizki/countries.git
```

### Testing

To testing with JVM, just running this command

```
$ make test
```

Will generate report for testing. In directory `app/build/reports/tests`

To generating code coverage report, running with this command

```
$ make coverage
```

Will generate report for code coverage. In directory `app/build/reports/jacoco`

## Built With

* Android Support libraries
* [Retrofit 2](http://square.github.io/retrofit/) - For Networking
* [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Better coding
* [Butterknife](https://github.com/JakeWharton/butterknife) - Used to View Injection
* [Timber](https://github.com/JakeWharton/timber) - For better logging
* [Glide](https://github.com/bumptech/glide) - Image Processing

## Authors

* [M Rizki Dafianto](https://github.com/mohamad-rizki)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
