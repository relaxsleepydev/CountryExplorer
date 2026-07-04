package com.example.countryexplorer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.decode.SvgDecoder
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.countryexplorer.data.CountryEntity

@Composable
fun CountryCard(
    country: CountryEntity,
    onClick: () -> Unit
) {
    // 1. Grab the context
    val context = LocalContext.current

    // 2. Build an ImageLoader that specifically knows how to read SVGs
    val svgImageLoader = remember(context) {
        coil.ImageLoader.Builder(context)
            .components {
                add(coil.decode.SvgDecoder.Factory())
            }
            .okHttpClient {
                okhttp3.OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
            .build()
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable{ onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = country.flagUrl,
                imageLoader = svgImageLoader, // <-- THE MAGIC FIX
                contentDescription = "${country.name} flag",
                modifier = Modifier.fillMaxWidth().height(120.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = country.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Capital: ${country.capital}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Population: ${country.population}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}