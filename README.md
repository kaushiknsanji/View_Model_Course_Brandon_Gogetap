# Android Architecture Components - ViewModels Course

Tutorial application for the [course about ViewModels in Android](https://www.udemy.com/android-arch-components-view-model/) by [Brandon Gogetap](https://www.udemy.com/user/brandongogetap/). 

---

## What one will learn

* Use of ViewModels.
* Use of LiveData to reactively update the UI.
* Decoupling Networking logic from the UI using ViewModels and LiveData.
* REST API Calls using Retrofit to download the Remote data in ViewModels.
* Dagger2 dependency injection for ViewModels.
* AutoValue to define immutable Value Types.

---

## Required Libraries/Dependencies

* Android Support library
* **ViewModel** for maintaining the data of the App and handling the network queries.
* **LiveData** to hold the data and notify its observers when the data changes.
* **[Butterknife](https://jakewharton.github.io/butterknife/)** for binding the View components.
* **[Retrofit](https://square.github.io/retrofit/)** for communicating with **[GitHub API](https://developer.github.com/v3/)**.
* **[Moshi](https://github.com/square/moshi)** to parse JSON into Java Objects.
* **[Dagger2](https://google.github.io/dagger/)** for Dependency Injection.
* **[AutoValue](https://github.com/google/auto/tree/master/value)** for defining immutable Value Types.
* Interdepencies of "Retrofit & Moshi", and "AutoValue & Moshi".

---

## Branches in this Repository

* **[feat_viewmodels](https://github.com/kaushiknsanji/View_Model_Course_Brandon_Gogetap/tree/feat_viewmodels)**
	* Covers the initial setup and implementation of ViewModels to load and display a list of Repositories and the details for a selected Repository.
  * Shows how to reload the app after process death (save-restore flow).
* **[feat_dagger](https://github.com/kaushiknsanji/View_Model_Course_Brandon_Gogetap/tree/feat_dagger)**
	* Covers the implementation of Dependency Injection using Dagger 2 for Networking logic and ViewModels.
* **[feat_autovalue](https://github.com/kaushiknsanji/View_Model_Course_Brandon_Gogetap/tree/feat_autovalue)**
	* Covers the AutoValue Implementation for Value Types.
	* Shows how using AutoValue makes comparison usecases like DiffUtil easier. 
* **[master](https://github.com/kaushiknsanji/View_Model_Course_Brandon_Gogetap)**
	* This is the main/default branch that covers all the above.
	
---

## Udemy Certificate

<a href="https://www.udemy.com/certificate/UC-WJ2696UY/">
<img alt="Udemy Certificate" src="https://udemy-certificate.s3.amazonaws.com/image/UC-WJ2696UY.jpg?l=en_US" width="50%">
</a>
