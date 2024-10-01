package com.example.bythen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.bythen.model.sepoliaChain
import com.example.bythen.ui.helper.ByteUtil
import com.example.bythen.ui.screen.OnBoardingScreen
import com.example.bythen.ui.theme.BythenTheme
import com.example.bythen.ui.theme.JokerPurple
import com.example.bythen.ui.theme.LotsoPink
import com.example.bythen.ui.theme.TotoroGray
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.android.relay.ConnectionType
import com.walletconnect.web3.modal.client.Modal
import com.walletconnect.web3.modal.client.Web3Modal
import com.walletconnect.web3.modal.presets.Web3ModalChainsPresets
import com.walletconnect.web3.modal.presets.Web3ModalChainsPresets.ethToken
import com.walletconnect.web3.modal.ui.components.internal.Web3ModalComponent
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val projectId = "224b0452720821b8e61fbb9a29fc4e2a"
        val connectionType = ConnectionType.AUTOMATIC
        val telemetryEnabled = true
        val appMetaData = Core.Model.AppMetaData(
            name = "Wallet Name",
            description = "Wallet Description",
            url = "kotlin.walletconnect.com",
            icons = listOf("https://raw.githubusercontent.com/WalletConnect/walletconnect-assets/master/Icon/Gradient/Icon.png"),
            redirect = "kotlin-wallet-wc:/request" // Custom Redirect URI
        )

        CoreClient.initialize(projectId = projectId, connectionType = connectionType, application = this.application, metaData = appMetaData, telemetryEnabled = telemetryEnabled) {
            println("haha $it")
        }

        Web3Modal.initialize(
            init = Modal.Params.Init(CoreClient),
            onSuccess = {
                // Callback will be called if initialization is successful
            },
            onError = { error ->
                // Error will be thrown if there's an issue during initialization
            }
        )

        Web3Modal.setChains(ByteUtil.getPreferredChain())

        val web3ModalModalDelegate = object : Web3Modal.ModalDelegate {
            override fun onSessionApproved(approvedSession: Modal.Model.ApprovedSession) {
                // Triggered when receives the session approval from wallet
                println("haha session approved")
            }

            override fun onSessionRejected(rejectedSession: Modal.Model.RejectedSession) {
                // Triggered when receives the session rejection from wallet
                println("haha session rejected")
            }

            override fun onSessionUpdate(updatedSession: Modal.Model.UpdatedSession) {
                // Triggered when receives the session update from wallet
                println("haha session updated")
            }

            override fun onSessionExtend(session: Modal.Model.Session) {
                // Triggered when receives the session extend from wallet
                println("haha session extend")
            }

            override fun onSessionEvent(sessionEvent: Modal.Model.SessionEvent) {
                // Triggered when the peer emits events that match the list of events agreed upon session settlement
                println("haha session event")
            }

            override fun onSessionDelete(deletedSession: Modal.Model.DeletedSession) {
                // Triggered when receives the session delete from wallet
                println("haha session delete")
            }

            override fun onSessionRequestResponse(response: Modal.Model.SessionRequestResponse) {
                // Triggered when receives the session request response from wallet
                println("haha session req response")
            }

            override fun onProposalExpired(proposal: Modal.Model.ExpiredProposal) {
                // Triggered when a proposal becomes expired
            }

            override fun onRequestExpired(request: Modal.Model.ExpiredRequest) {
                // Triggered when a request becomes expired
            }

            override fun onConnectionStateChange(state: Modal.Model.ConnectionState) {
                //Triggered whenever the connection state is changed
                println("haha session state change")
            }

            override fun onError(error: Modal.Model.Error) {
                // Triggered whenever there is an issue inside the SDK
                println("haha session error")
            }
        }

        Web3Modal.setDelegate(web3ModalModalDelegate)

        setContent {
            val web3ModalSheetState = rememberModalBottomSheetState(true)
            var showBottomSheet by remember { mutableStateOf(false) }
            val coroutineScope = rememberCoroutineScope()
            var hasWalletConnected by remember { mutableStateOf(false) }
            val hasSessionID by remember { mutableStateOf("") }

            fun onConnectWalletClick(){
                showBottomSheet = true
            }

            BythenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnBoardingScreen(
                        modifier = Modifier,
                        assets = listOf(
                            R.drawable.onboarding_illustration_1,
                            R.drawable.onboarding_illustration_2,
                            R.drawable.onboarding_illustration_3
                        ),
                        backgrounds = listOf(TotoroGray, LotsoPink, JokerPurple),
                        innerPadding = innerPadding,
                        onConnectWalletClick = ::onConnectWalletClick,
                    )
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                showBottomSheet = false
                            },
                            sheetState = web3ModalSheetState,
                            scrimColor = Color.Transparent
                        ) {
                            // Sheet content
                            Web3ModalComponent(shouldOpenChooseNetwork = false) {
                                coroutineScope.launch { web3ModalSheetState.hide() }.invokeOnCompletion {
                                    if (!web3ModalSheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}