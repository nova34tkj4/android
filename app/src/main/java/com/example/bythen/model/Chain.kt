package com.example.bythen.model

import com.walletconnect.web3.modal.client.Modal
import com.walletconnect.web3.modal.presets.Web3ModalChainsPresets.ethToken

val sepoliaChain = Modal.Model.Chain(
    chainName = "Sepolia",
    chainNamespace = "eip155",
    chainReference = "11155111",
    requiredMethods = listOf(),
    optionalMethods = listOf(),
    events = listOf(),
    token = ethToken,
    rpcUrl = "https://1rpc.io/sepolia",
    blockExplorerUrl = "https://sepolia.etherscan.io"
)