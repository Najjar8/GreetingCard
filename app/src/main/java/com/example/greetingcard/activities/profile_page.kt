package com.example.greetingcard.activities



import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.greetingcard.ui.theme.ErrorColor
import com.example.greetingcard.ui.theme.OnPrimary
import com.example.greetingcard.ui.theme.OnSurface
import com.example.greetingcard.ui.theme.OnSurfaceVariant
import com.example.greetingcard.ui.theme.OutlineVariant
import com.example.greetingcard.ui.theme.Primary
import com.example.greetingcard.ui.theme.Secondary
import com.example.greetingcard.ui.theme.SecondaryContainer
import com.example.greetingcard.ui.theme.SurfaceColor
import com.example.greetingcard.ui.theme.SurfaceContainerLow
import com.example.greetingcard.ui.theme.SurfaceContainerLowest


// ─────────────────────────────────────────────
//  Data models
// ─────────────────────────────────────────────
data class StatItem(
    val value: String,
    val label: String,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBg: Color
)

data class SettingsItem(
    val icon: ImageVector,
    val label: String,
    val trailing: @Composable (() -> Unit)? = null,
    val onClick: () -> Unit = {}
)

// ─────────────────────────────────────────────
//  Screen entry point
// ─────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    onSignOut: () -> Unit = {}
) {
    var darkModeEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBack    = { navController.popBackStack() },
                onSettings = { /* navigate to settings */ }
            )
        },
        bottomBar = {
            BottomNavBar(selectedIndex = 3)
        },
        containerColor = SurfaceColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            HeroSection()

            StatsSection()

            SettingsSection(
                darkModeEnabled = darkModeEnabled,
                onDarkModeToggle = { darkModeEnabled = it }
            )

            SignOutButton(onClick = onSignOut)

            Spacer(Modifier.height(8.dp))
        }
    }
}

// ─────────────────────────────────────────────
//  Top App Bar
// ─────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopBar(
    onBack: () -> Unit,
    onSettings: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize   = 18.sp
                ),
                color = OnSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = OnSurface
                )
            }
        },
        actions = {
            IconButton(onClick = onSettings) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings",
                    tint = OnSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SurfaceColor.copy(alpha = 0.8f)
        )
    )
}

// ─────────────────────────────────────────────
//  Hero / Profile Section
// ─────────────────────────────────────────────
@Composable
private fun HeroSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Avatar with gradient ring
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Primary, SecondaryContainer)
                        )
                    )
                    .padding(3.dp)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuA1iCAuUS4zXx5xJUwo2xcrFykvbTTjtJ1bpXBKLN42m5A4X9SH6mk99pC1vCniH4VYle94sPd-xnZnawkYSSEkkyNdaQdChw8PRarQxSSX_fO20GJZNIgkM7b36Q0zO8nbaoPxSXxhf6JAUfIcAWIq0YAL7MO4QoyJXnyEYICLDAu9JIE8lFq01wY4130jutnQL5mHhJy9W4toCokGkWCrzd72TqWsV9DES_tgI6FQ45xSMDMjlsipjiqT7Sz9GG9icE2HEWxwJqYJ",
                    contentDescription = "Alex Johnson",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(4.dp, SurfaceColor, CircleShape)
                )
            }

            // Edit FAB
            SmallFloatingActionButton(
                onClick = { /* open image picker */ },
                containerColor = Primary,
                contentColor   = OnPrimary,
                modifier = Modifier
                    .size(32.dp)
                    .offset(x = (-2).dp, y = (-2).dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit photo",
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text  = "Alex Johnson",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize   = 28.sp,
                letterSpacing = (-0.5).sp
            ),
            color = OnSurface
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text  = "Advanced Learning Track",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = OnSurfaceVariant
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { /* navigate to edit profile */ },
            shape   = RoundedCornerShape(12.dp),
            colors  = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor   = OnPrimary
            ),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text  = "Edit Profile",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.5.sp
                )
            )
        }
    }
}

// ─────────────────────────────────────────────
//  Stats / Bento Section
// ─────────────────────────────────────────────
@Composable
private fun StatsSection() {
    val stats = listOf(
        StatItem(
            value    = "24",
            label    = "Courses Completed",
            icon     = Icons.AutoMirrored.Outlined.MenuBook,
            iconTint = Primary,
            iconBg   = Primary.copy(alpha = 0.10f)
        ),
        StatItem(
            value    = "12",
            label    = "Certificates Earned",
            icon     = Icons.Outlined.WorkspacePremium,
            iconTint = Secondary,
            iconBg   = Secondary.copy(alpha = 0.10f)
        )
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text  = "Your Stats",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize   = 20.sp
                ),
                color = OnSurface
            )
            Text(
                text  = "View Analytics",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.clickable { /* navigate */ }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            stats.forEach { stat ->
                StatCard(stat = stat, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun StatCard(stat: StatItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(stat.iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = stat.icon,
                    contentDescription = null,
                    tint = stat.iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text  = stat.value,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize   = 32.sp,
                        letterSpacing = (-1).sp
                    ),
                    color = OnSurface
                )
                Text(
                    text  = stat.label.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight    = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        fontSize      = 10.sp
                    ),
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

// ─────────────────────────────────────────────
//  Settings Section
// ─────────────────────────────────────────────
@Composable
private fun SettingsSection(
    darkModeEnabled: Boolean,
    onDarkModeToggle: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text  = "Settings",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize   = 20.sp
            ),
            color = OnSurface
        )

        Surface(
            shape  = RoundedCornerShape(16.dp),
            color  = SurfaceContainerLow,
            tonalElevation = 0.dp
        ) {
            Column {
                // Account Settings
                SettingsRow(
                    icon  = Icons.Outlined.PersonOutline,
                    label = "Account Settings",
                    trailing = {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = OnSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    onClick = { /* navigate */ }
                )

                HorizontalDivider(
                    color = OutlineVariant.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                // Language
                SettingsRow(
                    icon  = Icons.Outlined.Translate,
                    label = "Language",
                    trailing = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text  = "English",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = Primary
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = null,
                                tint = OnSurfaceVariant,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    },
                    onClick = { /* navigate */ }
                )

                HorizontalDivider(
                    color = OutlineVariant.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                // Dark Mode (non-clickable row with switch)
                SettingsRow(
                    icon  = Icons.Outlined.DarkMode,
                    label = "Dark Mode",
                    trailing = {
                        Switch(
                            checked         = darkModeEnabled,
                            onCheckedChange = onDarkModeToggle,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor      = OnPrimary,
                                checkedTrackColor      = Primary,
                                uncheckedThumbColor    = OnSurface,
                                uncheckedTrackColor    = OutlineVariant.copy(alpha = 0.3f),
                                uncheckedBorderColor   = Color.Transparent
                            )
                        )
                    },
                    onClick = { onDarkModeToggle(!darkModeEnabled) }
                )
            }
        }
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    trailing: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                shape  = CircleShape,
                color  = SurfaceContainerLowest,
                shadowElevation = 1.dp,
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = OnSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text  = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = OnSurface
            )
        }
        trailing()
    }
}

// ─────────────────────────────────────────────
//  Sign Out Button
// ─────────────────────────────────────────────
@Composable
private fun SignOutButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape    = RoundedCornerShape(12.dp),
        border   = BorderStroke(2.dp, ErrorColor.copy(alpha = 0.2f)),
        colors   = ButtonDefaults.outlinedButtonColors(contentColor = ErrorColor)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text  = "Sign Out",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight    = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        )
    }
}

// ─────────────────────────────────────────────
//  Bottom Navigation Bar
// ─────────────────────────────────────────────
private data class NavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit = {}
) {
    val items = listOf(
        NavItem("Learn",    Icons.Outlined.School,       Icons.Filled.School),
        NavItem("Search",   Icons.Outlined.Search,       Icons.Filled.Search),
        NavItem("Progress", Icons.Outlined.Leaderboard,  Icons.Filled.Leaderboard),
        NavItem("Profile",  Icons.Outlined.Person,       Icons.Filled.Person)
    )

    NavigationBar(
        containerColor = SurfaceContainerLowest.copy(alpha = 0.95f),
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                ambientColor = Color.Black.copy(alpha = 0.06f)
            )
    ) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex
            NavigationBarItem(
                selected = selected,
                onClick  = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text  = item.label.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight    = FontWeight.SemiBold,
                            letterSpacing = 0.8.sp,
                            fontSize      = 9.sp
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor       = Primary,
                    selectedTextColor       = Primary,
                    indicatorColor          = SurfaceContainerLow,
                    unselectedIconColor     = OnSurfaceVariant,
                    unselectedTextColor     = OnSurfaceVariant
                )
            )
        }
    }
}

// ─────────────────────────────────────────────
//  Preview
// ─────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(navController = rememberNavController())
    }
}