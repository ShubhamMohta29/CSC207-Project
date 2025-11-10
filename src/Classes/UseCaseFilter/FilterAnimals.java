package Classes.UseCaseFilter;
/**
 * Building filters with specifications
 */

/*
 * Multi-select filters - location, diet etc
 * Multi-level filters - based on taxonomy since heirarchical by nature?
 * Range filters (weight, lifespan etc?)
 * Sorting (by alphabetical order, size,rarity? etc
 * Pagination (20 uploads --> figure out a way to upload results dynamically instead of having the user click *apply* or smth
 * In - memory Cache results for optimization? --> avoid repeated api calls
 * suggest recommendations based on latest search?
 * more ui/ux - visually mark endangered animals - maybe like a border around that or smth
 * more fancy -- data analytics ( bonus if it doesn't slow down/costly)
 * reset filter button
 */


    /*
    if following the clean architecture format, you'd need a class for input boundary, output boundary,
    and a filterusecaseinteractor interface thats implemeted by the actual filter animals class? input takes
     in  all filter criterias --> declares them, creates an input object and then you have the getters. output
     takes the list of filtered animals, creates an output object and getters? and then u have an input boundary
     interface, an output boundary interface and also an interactor should connect the input and output + business
     logic
     the input and output?
     */
public class FilterAnimals {
}
