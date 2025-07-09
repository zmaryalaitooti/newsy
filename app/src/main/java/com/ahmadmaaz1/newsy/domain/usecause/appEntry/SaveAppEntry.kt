package com.ahmadmaaz1.newsy.domain.usecause.appEntry

import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager

class SaveAppEntry(val localUserManager: LocalUserManager) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}