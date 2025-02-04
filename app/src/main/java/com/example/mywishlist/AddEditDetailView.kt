package com.example.mywishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){
    Scaffold(topBar = {
        AppBarView(title = if(id != 0L){
            "Update List"
        }
        else{
           "Add List"
        }){navController.navigateUp() }
    }) {
        Column (modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier=Modifier.height(10.dp))

            WishTextField(label="title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                })

            Spacer(modifier=Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {viewModel.onWishDescriptionChanged(it)}
            )
            Spacer(modifier=Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()){
                    //update wish
                }else{
                    //add wish
                }
            }
            ) {
                Text(text=if(id!=0L) "Update Wish"
                else "Add Wish", color = colorResource(R.color.white)
                )
            }

        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value:String,
    onValueChanged: (String) -> Unit
){

    OutlinedTextField(
        value=value,
        onValueChange=onValueChanged,
        label= { Text(label)},
        modifier=Modifier.fillMaxWidth().padding(horizontal = 10.dp)
        )
}