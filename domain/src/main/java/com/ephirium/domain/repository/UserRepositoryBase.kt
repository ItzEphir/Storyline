package com.ephirium.domain.repository

import com.ephirium.domain.dto.UserDtoBase

interface UserRepositoryBase<T : UserDtoBase> : Repository<List<T>>