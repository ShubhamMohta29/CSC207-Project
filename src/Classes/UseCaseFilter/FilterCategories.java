package Classes.UseCaseFilter;

/**
 * Main and sub categories for the animals
 */


/**
 * Folder Structure
 * UseCaseFilter
 *      UseCase
 *          FilterInput -- > takes in the parameter for filter logic and creates a query object
 *          FilterInputBoundaryInterface
 *          FilterOutput --> returns the filter object (list of animals)
 *          FilterUseCaseInteractor -->
 *          FilterController --> extract the input from FilterView
 *          FilterView --> essentailly the filtergui
 *
 *
 *      Controller
 *      ExternalInterfaces
 */
    enum AnimalCategory{
        MAMMALS,
        BIRDS,
        REPTILE,
        AMPHIBIAN,
        FISH,
        INSECT,
        ARACHNID,
        CNIDARIA
        //add more as needed
    }
    enum Diet{
        HERBIVORE,
        CARNIVORE,
        OMNIVORE

    }
    enum Locations{
        AFRICA,
        ASIA,
        CENTRAL_AMERICA,
        EUROPE,
        NORTH_AMERICA,
        SOUTH_AMERICA
    }
    //ADD MORE ENUMS AS NEEDED
