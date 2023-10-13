# Playground management

Run the application from IDE or by executing:

```
./gradlew bootRun
```

On startup, the application generates 100 kids. Their information and tickets can be viewed by calling:

```
GET localhost:8080/kids
```

There's a postman collection attached at the projects root dir

```
Play site management.postman_collection.json
```

# Some logic explanation

Play site equipment is stored in the order it is provided in the creation request. Kids are assigned to equipment in the
order of the play sites equipment. i.e. if the play site has a double swing and a slide, but only 1 kid is at the site,
the site will have 0% utilization due to the double swing's utilization calculation logic.

There is no separate queue implementation for kids playing vs kids waiting, as I didn't see a need for it. Kids are
stored on the site in order, so this can be distinguished easily based on the kids index vs the total capacity of the
site.