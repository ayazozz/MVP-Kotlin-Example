
# MVP Kotlin Example
Recipes App 


This mobile application shows recipes. Also, you can view details of recipes.
- Rxjava
- Recyclerview

###  The flow Schema Of Recipes Application

###  Activities - Fragment- Classes
 When the project create, MVP design pattern is used.
1- FirstLaunchActivity
 Its for launcher screen. It waits 3 seconds , then goes to DisplayRecipesActivity.
2- DisplayAllRecipes Package
 2.a- DisplayRecipesActivity

 It implements AppCompatActivity(), RecipesAdapter.Listener,
DisplayRecipesPresenterImp.DisplayRecipesView. A presenter object has created to get recipes by
rest api requests from DisplayRecipesPresenterImp class. When user clicks on any recipe item, activity goes
to DisplayRecipeDetailsFragment to show more details.
Also, there are overrode methods for update view and listen item click event.
 2.b- DisplayRecipesPresenter - Interface

 It has one function to get recipes.
 2.c- DisplayRecipesPreseneterImpl

 It s an implementation class of interface. In this class, api request has sent to web service and handled the
response. At the end of handing process, RecipeDataModel list is obtained. This RecipeDataModel list
includes title, photo url, description and id of each recipes. Finally, this list is sent to view function where in
the activity to display the screen.
 2.d- RecipeViewHolder - RecipesAdapter

 They are the class to display list datas by using recycler view.
3- DisplayRecipeDetails Package
 3.a- DisplayRecipeDetails
 It’s a fragment to view more details of selected recipe. It gives title, image, chef name, description and
features infos of recipes. Also, this fragment gets RecipeDataModel object sent by DisplayRecipesActivity.
Thus, app doesn’t have to spend time to get same information again from rest api. At the same way, this
fragment has presenter to get details.
 3.b- DisplayRecipeDetailsPresenter

 It has one function to get recipe details.
 3.c- DisplayRecipeDetailsPresenterImp
 It s an implementation class of interface. In this class, api request has sent to web service and handled the
response. At the end of handing process, recipe features list and chef name are obtained. Finally, this
informations are sent to view function where in the fragment to display the screen.
4- Api Request
 Its a interface which includes rest api functions and creating http connection.
5- DataModel Package
5.a- RecipeModel
 It’s a data model for each item of recipes list.
5.b- RecipeDetailModel
 It’s a data model for details of selected recipe.
5.c- RecipeDataModel
 It’s a data model to send already received data.
