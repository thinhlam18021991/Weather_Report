
# Weather report App

The purpose of this document is to provide developers the overview of this project.


## Systems Requirement

- Support from **Android 7.0** and later 
- Target **Android 12**
 
## App Architecture

- Project apply MVVM+Clean architect for each feature module.
- There are 3 layers in this project:
  - Domain: Repository(interface), use case.
  - Data: Repository Implement
  - Presentation: Activity, Adapter, ViewModel, Dagger Injection.
- Dependencies between 3 layers:
<img src="https://user-images.githubusercontent.com/71365481/93423718-cfbf3980-f8e0-11ea-9ea3-81c1ac845ebb.png">

## Code folder structure 
### Project Structure

<img width="391" alt="Screen Shot 2022-09-27 at 17 29 00" src="https://user-images.githubusercontent.com/74524101/192502891-27cd278a-11d7-49fb-92a6-6b195854f036.png">

- app module (feature module): feature module and it is also main module, because in this app we have 1 feature only.
- non-feature modules (feature modules can depend on it, it prevent decouple between feature modules)
  - base module: contain base class using in this app such as base viewholder, dialog, etc.
  - model module: contain all models is being used in this app.
  - network: provide everything related to network for this app such as retrofit, okhttp, caching handling, etc.
  - networkmodel module: contain model we got from API.
  - security module: provide sensitive data, here is appId
  - share module: share class to use throughout this app, example network module need application class so we will use this module to provide application for network module
  - theme module: provide common resources (dimension, color, theme, style)
  - utils module: provide utils such as dateutils, currency utils for feature module or non-feature module if they need

### Unit Test
<img width="1189" alt="Screen Shot 2022-09-27 at 18 01 51" src="https://user-images.githubusercontent.com/74524101/192508887-d4bd44f9-4339-471f-80a6-14821cbce1a5.png">

- Total Code Coverage for whole project is 56%
- Unit test for Use case, Repository, ViewModel, Utils, ViewHolder, Model, Model Mapper.

## Requirement steps to run application

- Build App with file jks in keystore folder
- For unit test, please check app module, model module, utils module

## Checklist of requirements has done. 
1. Using Kotlin 
2. Implementing MVVM + Clean Architecture
3. Apply Live Data mechanism
4. UI looks like in attachment
5. Write UnitTests, please check app module, model module, utils module
6. Acceptance Tests
7. Exception handing - show **Dialog** when call API fail
8. Cache handling - using okhttp caching
9. Secure Android app from 
* Store appId with C++
* Use okhttp tls to set certification in app side (certificate pining) to make sure our request call to correct server, but I dont have certificate so I write an function to set with empty certificate, please check function applyCertificateIfNeeded in Certificate Object
* Decompile APK - using **proguardFiles** 
* Rooted device - using **Rootbeer** , we can use **SafetyNet** to check also, **SafetyNet** more accurate but it require network and google api key, in this project I am using RootBear
10. Accessibility for Disability Supports - WeatherApp supported for **talkback** and **scalling text** automatically
11. Entity relationship diagram, app architecture diagram. 
12. Readme file includes

