package com.ephirium.domain.repository

import com.ephirium.domain.dto.PostDtoBase

interface PostRepositoryBase<T : PostDtoBase> : Repository<List<T>>