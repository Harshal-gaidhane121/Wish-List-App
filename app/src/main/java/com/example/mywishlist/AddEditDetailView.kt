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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlist.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage= remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    Scaffold(topBar = {
        AppBarView(title = if(id != 0L){
            "Update List"
        }
        else{
           "Add List"
        }){navController.navigateUp() }
    },scaffoldState=scaffoldState
        ) {
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
                    //add wish
                    if(id !=0L ){
                    //update wish

                    }
                    else{
                        //add wish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value="Wish has been created"
                    }
                }
                else{
                    snackMessage.value="Enter the required fields"
                }

                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
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