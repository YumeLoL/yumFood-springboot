// pagination and search by name
const getSetmealPage = (params) => {
  return $axios({
    url: '/setmeal/page',
    method: 'get',
    params
  })
}

// delete
const deleteSetmeal = (ids) => {
  return $axios({
    url: '/setmeal',
    method: 'delete',
    params: { ids }
  })
}

// eidt
const editSetmeal = (params) => {
  return $axios({
    url: '/setmeal',
    method: 'put',
    data: { ...params }
  })
}

// add
const addSetmeal = (params) => {
  return $axios({
    url: '/setmeal',
    method: 'post',
    data: { ...params }
  })
}

// search by id
const querySetmealById = (id) => {
  return $axios({
    url: `/setmeal/${id}`,
    method: 'get'
  })
}

// switch status
const setmealStatusByStatus = (params) => {
  return $axios({
    url: `/setmeal/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
