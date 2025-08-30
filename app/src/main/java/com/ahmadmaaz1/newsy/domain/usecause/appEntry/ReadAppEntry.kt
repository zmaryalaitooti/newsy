package com.ahmadmaaz1.newsy.domain.usecause.appEntry

import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (val localUserManager: LocalUserManager) {
     operator fun invoke(): Flow<Boolean>{
      return  localUserManager.readAppEntry()
    }
}