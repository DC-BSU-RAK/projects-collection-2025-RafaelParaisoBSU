package com.example.rafpizza

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Data class for storing pizza combo information
    data class PizzaCombo(
        val ingredient1: String,
        val ingredient2: String,
        val backgroundImageRes: Int
    )

    // Selected ingredients by user
    private val selectedIngredients = mutableListOf<String>()

    // UI components
    private lateinit var resultPizzaImage: ImageView
    private lateinit var item1Image: ImageView
    private lateinit var item2Image: ImageView
    private lateinit var bakeButton: Button
    private lateinit var clearButton: Button
    private lateinit var exitResultButton: ImageButton

    private lateinit var infoButton: ImageButton
    private lateinit var infoImage: ImageView
    private lateinit var exitInfoButton: ImageButton

    // List of valid pizza combinations
    private val pizzaCombos = listOf(
        PizzaCombo("Pepperoni", "Ham", R.drawable.meatlovers),
        PizzaCombo("Pepperoni", "Bacon", R.drawable.baconatorpizza),
        PizzaCombo("Pepperoni", "Peppers", R.drawable.pepperonisupreme),
        PizzaCombo("Pepperoni", "Pineapple", R.drawable.tropicalpepperoni),
        PizzaCombo("Ham", "Bacon", R.drawable.baconandham),
        PizzaCombo("Ham", "Peppers", R.drawable.hamsupreme),
        PizzaCombo("Ham", "Pineapple", R.drawable.hawaiianpizza),
        PizzaCombo("Bacon", "Peppers", R.drawable.baconsupreme),
        PizzaCombo("Bacon", "Pineapple", R.drawable.sweetsizzle),
        PizzaCombo("Peppers", "Pineapple", R.drawable.veggiehawaiian)
    )

    // Ingredient image references
    private val ingredientImages = mapOf(
        "Pepperoni" to R.drawable.pepperoni,
        "Bacon" to R.drawable.bacon,
        "Peppers" to R.drawable.bellpeppers,
        "Pineapple" to R.drawable.pineapple,
        "Ham" to R.drawable.ham
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views to variables
        resultPizzaImage = findViewById(R.id.resultPizzaImage)
        item1Image = findViewById(R.id.item1_pic)
        item2Image = findViewById(R.id.item2_pic)
        bakeButton = findViewById(R.id.bakeButton)
        clearButton = findViewById(R.id.clearButton)
        exitResultButton = findViewById(R.id.exitResultButton)
        infoButton = findViewById(R.id.infoButton)
        infoImage = findViewById(R.id.infoImage)
        exitInfoButton = findViewById(R.id.exitInfoButton)

        // Hide result and info screens at startup
        resultPizzaImage.visibility = ImageView.GONE
        exitResultButton.visibility = ImageButton.GONE
        infoImage.visibility = ImageView.GONE
        exitInfoButton.visibility = ImageButton.GONE

        // Map buttons to ingredients
        val buttons = mapOf(
            R.id.pepperoniButton to "Pepperoni",
            R.id.baconButton to "Bacon",
            R.id.bellpeppersButton to "Peppers",
            R.id.pineappleButton to "Pineapple",
            R.id.hamButton to "Ham"
        )

        // Set up listeners for ingredient buttons
        buttons.forEach { (id, ingredient) ->
            val button = findViewById<ImageButton>(id)
            setupIngredientButton(button, ingredient)
        }

        // Set up bake and clear buttons
        bakeButton.setOnClickListener { onBakePressed() }
        clearButton.setOnClickListener { clearSelection() }

        // Close result screen
        exitResultButton.setOnClickListener {
            clearSelection()
        }

        // Show info screen
        infoButton.setOnClickListener {
            infoImage.visibility = ImageView.VISIBLE
            exitInfoButton.visibility = ImageButton.VISIBLE
        }

        // Hide info screen
        exitInfoButton.setOnClickListener {
            infoImage.visibility = ImageView.GONE
            exitInfoButton.visibility = ImageButton.GONE
        }
    }

    // Sets up an ingredient button with animations and click behavior
    private fun setupIngredientButton(button: ImageButton, ingredient: String) {
        val idleAnim = AnimationUtils.loadAnimation(this, R.anim.idle_hover)
        val clickAnim = AnimationUtils.loadAnimation(this, R.anim.click_move)

        // Start idle animation
        button.startAnimation(idleAnim)

        button.setOnClickListener {
            // Do nothing if already selected or 2 ingredients are selected
            if (selectedIngredients.contains(ingredient) || selectedIngredients.size >= 2) return@setOnClickListener

            // Play click animation
            button.clearAnimation()
            button.startAnimation(clickAnim)

            // Restart idle animation after click
            clickAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    button.postDelayed({ button.startAnimation(idleAnim) }, 50)
                }
            })

            // Add ingredient to list and show image
            selectedIngredients.add(ingredient)
            ingredientImages[ingredient]?.let {
                if (selectedIngredients.size == 1) item1Image.setImageResource(it)
                else if (selectedIngredients.size == 2) item2Image.setImageResource(it)
            }
        }
    }

    // Called when the "Bake" button is pressed
    private fun onBakePressed() {
        // Require exactly two ingredients
        if (selectedIngredients.size < 2) {
            showErrorDialog("Please select 2 ingredients before baking.")
            return
        }

        // Check for a valid pizza combo
        val combo = findPizzaCombo(selectedIngredients[0], selectedIngredients[1])

        if (combo != null) {
            // Show resulting pizza image
            resultPizzaImage.setImageResource(combo.backgroundImageRes)
            resultPizzaImage.visibility = ImageView.VISIBLE
            exitResultButton.visibility = ImageButton.VISIBLE
        } else {
            // Invalid combination
            resultPizzaImage.visibility = ImageView.GONE
            exitResultButton.visibility = ImageButton.GONE
            showErrorDialog("That combination doesn't make a valid pizza.")
        }
    }

    // Clears the current selection and result
    private fun clearSelection() {
        selectedIngredients.clear()
        item1Image.setImageDrawable(null)
        item2Image.setImageDrawable(null)
        resultPizzaImage.setImageDrawable(null)
        resultPizzaImage.visibility = ImageView.GONE
        exitResultButton.visibility = ImageButton.GONE
    }

    // Finds a matching pizza combo regardless of ingredient order
    private fun findPizzaCombo(ing1: String, ing2: String): PizzaCombo? {
        return pizzaCombos.find {
            (it.ingredient1 == ing1 && it.ingredient2 == ing2) ||
                    (it.ingredient1 == ing2 && it.ingredient2 == ing1)
        }
    }

    // Shows an alert dialog with an error message
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Invalid Combo")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
