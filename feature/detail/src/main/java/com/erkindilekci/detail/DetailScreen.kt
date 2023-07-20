package com.erkindilekci.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.erkindilekci.components.CustomButton
import com.erkindilekci.components.CustomInputText
import com.erkindilekci.detail.component.BackButton
import com.erkindilekci.detail.component.InfoCard
import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.erkindilekci.ui.theme.Orange
import com.erkindilekci.ui.theme.Yellow
import com.erkindilekci.util.FormatCoinPrice.Companion.formatPrice
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    name: String,
    price: Float,
    symbol: String,
    lastDayChange: Float,
    lastOneHourChange: Float
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val formattedPrice = formatPrice(price.toDouble())
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                name,
                symbol,
                price.toString(),
                viewModel,
                navHostController
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(text = name, fontSize = 36.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .size(90.dp, 30.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(50.dp),
                backgroundColor = Color(0xFFF0F0F0),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = symbol,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "$$formattedPrice",
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(30.dp))
            DetailInfoSection(
                lastDayChange.toDouble(),
                lastOneHourChange.toDouble()
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                text = "Add To Portfolio",
                color = Color(0xFF0066FF),
                textColor = Color.White,
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            ) {}

            CustomButton(
                text = "Add To Favorite",
                onClick = {
                    val favorite = FavoritesEntity(symbol)
                    viewModel.addToFavorite(favorite)
                    navHostController.navigate("favorites_screen")
                }
            ) {}
        }
    }
}

@Composable
fun DetailInfoSection(
    lastDayChange: Double,
    lastOneHourChange: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard(lastDayChange, "24h Change")
        InfoCard(lastOneHourChange, "1h Change")
    }
}

@Composable
fun BottomSheet(
    name: String,
    symbol: String,
    price: String,
    viewModel: DetailViewModel,
    navHostController: NavHostController
) {
    var priceState by remember { mutableStateOf(price) }
    var amountState by remember { mutableStateOf("0.0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomInputText(
            labelText = "Price",
            keyboardType = KeyboardType.Number,
            initialText = priceState,
            backgroundColor = Color.White,
            textColor = Orange
        ) {
            priceState = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomInputText(
            labelText = "Amount",
            keyboardType = KeyboardType.Number,
            initialText = amountState,
            backgroundColor = Color.White,
            textColor = Orange
        ) {
            amountState = it
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            width = 260.dp,
            height = 50.dp,
            text = "Add to Portfolio",
            onClick = {
                val totalPrice = priceState.toFloat() * amountState.toFloat()

                val portfolio = PortfolioModel(
                    name = name,
                    symbol = symbol,
                    buyingPrice = priceState,
                    amount = amountState,
                    totalPrice = totalPrice.toString()
                )
                viewModel.addToPortfolio(portfolio)
                navHostController.navigate("portfolio_screen")
            }
        ) {}
    }
}
