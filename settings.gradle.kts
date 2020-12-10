rootProject.name = "advent-of-code-2020"

include(":util")
for (i in 1..10) {
    include(":day-${i.toString().padStart(2, '0')}")
}
