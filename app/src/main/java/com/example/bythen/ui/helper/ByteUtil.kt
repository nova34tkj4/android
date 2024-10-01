package com.example.bythen.ui.helper

import com.example.bythen.BuildConfig
import com.example.bythen.model.sepoliaChain
import com.walletconnect.web3.modal.client.Modal
import com.walletconnect.web3.modal.presets.Web3ModalChainsPresets

object ByteUtil {
    fun getPreferredChain(): List<Modal.Model.Chain> {
        return if (BuildConfig.FLAVOR == "production")
            return listOf(Web3ModalChainsPresets.ethChains.values.first())
        else listOf(sepoliaChain)
    }
}