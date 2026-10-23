package dev.repro.sheetpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { MaterialTheme { Screen() } }
  }
}

private val pageColors = listOf(Color(0xFFEF5350), Color(0xFF66BB6A), Color(0xFF42A5F5), Color(0xFFFFA726))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen() {
  var showSheet by remember { mutableStateOf(false) }

  Column(Modifier.fillMaxSize().safeDrawingPadding()) {
    Text("Material3 ${BuildConfig.MATERIAL3_VERSION}", Modifier.padding(16.dp))
    Text("Inline pager", Modifier.padding(horizontal = 16.dp))
    Pager()
    Button(onClick = { showSheet = true }, modifier = Modifier.padding(16.dp)) {
      Text("Open pager in ModalBottomSheet")
    }
  }

  if (showSheet) {
    ModalBottomSheet(
      onDismissRequest = { showSheet = false },
      sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
      Text("Pager in ModalBottomSheet", Modifier.padding(horizontal = 16.dp))
      Pager()
      Spacer(Modifier.height(48.dp))
    }
  }
}

@Composable
private fun Pager() {
  val state = rememberPagerState { pageColors.size }
  HorizontalPager(state, Modifier.fillMaxWidth().height(300.dp)) { page ->
    Box(Modifier.fillMaxSize().padding(16.dp).background(pageColors[page])) {
      Text("Page ${page + 1}", Modifier.padding(16.dp), style = MaterialTheme.typography.headlineMedium)
    }
  }
}
