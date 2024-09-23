import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.felipe.brterritory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BRTopBar(
    currentPage: String,
    onMenuClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentPage,
                fontSize = dimensionResource(id = R.dimen.app_bar_text_size).value.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.primaryColor)
        )
    )
}
