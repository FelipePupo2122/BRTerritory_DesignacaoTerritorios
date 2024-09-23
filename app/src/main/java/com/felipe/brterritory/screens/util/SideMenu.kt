import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(onDismiss: () -> Unit, onPageSelected: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .background(Color.Gray)
            .padding(top = 16.dp)
    ) {
        Text(
            "Home",
            modifier = Modifier
                .clickable {
                    onPageSelected("Home")
                    onDismiss()
                }
                .padding(vertical = 12.dp),
            fontSize = 20.sp
        )
        Text(
            "Register Territory",
            modifier = Modifier
                .clickable {
                    onPageSelected("Register Territory")
                    onDismiss()
                }
                .padding(vertical = 12.dp),
            fontSize = 20.sp
        )
        Text(
            "Rent Territory",
            modifier = Modifier
                .clickable {
                    onPageSelected("Rent Territory")
                    onDismiss()
                }
                .padding(vertical = 12.dp),
            fontSize = 20.sp
        )
        Text(
            "View Rented Territories",
            modifier = Modifier
                .clickable {
                    onPageSelected("View Rented Territories")
                    onDismiss()
                }
                .padding(vertical = 12.dp),
            fontSize = 20.sp
        )
        Text(
            "Register Leader",
            modifier = Modifier
                .clickable {
                    onPageSelected("Register Leader")
                    onDismiss()
                }
                .padding(vertical = 12.dp),
            fontSize = 20.sp
        )
    }
}
