package com.example.recipeapp.ui.displayRecipeDetails


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.recipeapp.R
import com.example.recipeapp.data.model.RecipeDataModel
import com.squareup.picasso.Picasso

class RecipeDetailFragment : Fragment(), RecipeDetailsPresenterImpl.RecipeDetailView {


    private lateinit var recipeModel: RecipeDataModel
    private lateinit var presenter: RecipeDetailsPresenter

    private lateinit var recipetitle: TextView
    private lateinit var recipePhoto: ImageView
    private lateinit var recipeChef: TextView
    private lateinit var recipeDescription: TextView
    private lateinit var recipeTagList: ListView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_display_recipe_details, container, false)
        initialization(view)

        presenter.getDetails(recipeModel.recipeId)

        return view
    }


    private fun initialization(view: View) {
        val args = arguments
        if (args != null)
            recipeModel = args.getParcelable<RecipeDataModel>("RECIPE_DETAILS")!!

        presenter = RecipeDetailsPresenterImpl(this)

        recipePhoto = view.findViewById(R.id.recipe_img)
        recipetitle = view.findViewById(R.id.recipe_title)
        recipeChef = view.findViewById(R.id.recipe_chef)
        recipeDescription = view.findViewById(R.id.rescipe_description)
        recipeTagList = view.findViewById(R.id.tag_list_view)

    }

    override fun getAccessToken(): String {
        return resources.getString(R.string.access_token)
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun bindDetailInfo(recipeTags: MutableList<String>, recipeChefName: String) {


        Picasso.get().load("https:" + recipeModel.recipePhotoUrl)
            .resize(960, 420)
            .into(recipePhoto)
        recipetitle.text = recipeModel.recipeTitle
        recipeDescription.text = recipeModel.recipeDescription
        recipeChef.text = "CHEF : " + recipeChefName

        val adapter = ArrayAdapter<String>(context!!, R.layout.recipe_tag_row_layout, recipeTags)
        recipeTagList.adapter = adapter

    }


    companion object {

        @JvmStatic
        fun newInstance(recipe: RecipeDataModel) =
            RecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("RECIPE_DETAILS", recipe)

                }
            }
    }


}

