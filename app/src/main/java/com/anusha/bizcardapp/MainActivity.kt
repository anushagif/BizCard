package com.anusha.bizcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anusha.bizcardapp.ui.theme.BizCardAppTheme
import com.anusha.bizcardapp.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BizCard()
                }
            }
        }
    }
}

@Composable
fun BizCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(13.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Top) {
                CreateImageProfile()
                Divider(
                color= Color.Gray,
                thickness = 6.dp)
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text(text = "Portfolio",
                    style = Typography.button)
                    
                }
                if(buttonClickedState.value){
                    Content()
                }else{
                    Box {
                    }
                }
            }

        }

    }
}

@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxSize(),
        border = BorderStroke(2.dp, Color.LightGray),
            shape = RoundedCornerShape(corner = CornerSize(6.dp))
        ) {
            Portfolio(data = listOf<String>("Project 1", "Project2", "Project3"))
        }
    }
    
}

@Composable
fun Portfolio(data: List<String>) {
LazyColumn{
    items(data){item ->
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
            shape = RectangleShape,
        elevation = 4.dp) {
            Row(modifier = Modifier.padding(8.dp)
                .background(MaterialTheme.colors.background)
                .padding(8.dp)) {
               CreateImageProfile(modifier = Modifier.size(100.dp))
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(text = item, fontWeight = FontWeight.Bold)
                    Text(text = "A great project indeed",fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

}

@Composable
private fun CreateInfo() {
    Column {
        Text(
            text = "Anushkhan C",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Android Compose Programmer",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@annua",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            contentScale = ContentScale.FillBounds,
            modifier = modifier.size(135.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardAppTheme {
       BizCard()
    }
}