// pagination and search dish
const getDishPage = (params) => {
  return $axios({
    url: '/dish/page',
    method: 'get',
    params
  })
}

// add new
const addDish = (params) => {
  return $axios({
    url: '/dish',
    method: 'post',
    data: { ...params }
  })
}



// get a dish info by id
const queryDishById = (id) => {
  return $axios({
    url: `/dish/${id}`,
    method: 'get'
  })
}

// edit
const editDish = (params) => {
  return $axios({
    url: '/dish',
    method: 'put',
    data: { ...params }
  })
}


// delete
const deleteDish = (ids) => {
  return $axios({
    url: '/dish',
    method: 'delete',
    params: { ids }
  })
}


// get category list
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 查菜品列表的接口
const queryDishList = (params) => {
  return $axios({
    url: '/dish/list',
    method: 'get',
    params
  })
}

// img download
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售---批量起售停售接口
const dishStatusByStatus = (params) => {
  return $axios({
    url: `/dish/status/${params.status}`,
    method: 'post',
    params: { ids: params.id }
  })
}