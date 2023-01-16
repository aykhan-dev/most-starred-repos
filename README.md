# most-starred-repos
It's a simple native android project for demonstration of architectural and technological skills in Kotlin.

# general-overview-of-architecture
* The basic form of clean architecture is applied to the project (`data`-`domain`-`presentation`)
* As a design pattern, `MVVM` is used
* Whole project splitted into several layers, such as 1.`view` 2.`viewModel` 3.`use case` 4.`repository` 5.`data source`;
* For UI construction, the declarative way of `Jetpack Compose` is preferred

# used-techs
* `Hilt DI`
* `Jetpack Compose`
* `Google Accompanist for additional Jetpack Compose features`
* `Retrofit`
* `OkHttp`
* `KotlinX serialization`
* `Coil Image Loader`
* `Kotlin Coroutines`
* `Room persistence library`
* `Timber Logger`

# used-3rd-party-libs
* `Retrofit` (making network requests)
* `OkHttp` (making network requests)
* `KotlinX` (JSON parsing)
* `Coil Image Loader` (Jetpack compose image loading)
* `Timber` (Usual logging stuff)

# features
* App simply request for github repositories in descending order of start count
* App retreives data in format of paged data with `10` `per_page` size for repositories `in last 30 days`
* App caches retreived data in the local database and directly serves the data from there to UI
* As a user, it's possible to see the `name`, `description`, `start-count`, `owner-name` and `owner-profile-image` in the UI

# challanges
* The format of incoming JSON is not perfect for keeping the track of page index via relying on the retreived data
* The details about `prev`, `next`, `first` and `last` pages' links come in the header section of the response with key `link`

# solutions
* `Paging3` library is overengineered solution for this kind of challanges, that's why I implemented pageIndex tracking by myself
* On each data retreival, it saves the page index of each item where they belong right before the caching
* Pagination is triggered by scroll detection of `LazyColumn`
* On each fetch request, the upcoming page index is determined by the last cached item's page index. Increasing it by one is enough
* When pagination ends, the JSON schema of the response changes and this stops the whole process via a handled exception throwing
