// get all category
const getCategoryPage = (params) => {
  return $axios({
    url: '/category/page',
    method: 'get',
    params
  })
}


// add new dish or add new promos category
const addCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'post',
    data: { ...params }
  })
}


// edit category
const editCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'put',
    data: { ...params }
  })
}


// delete category
const deleCategory = (ids) => {
  return $axios({
    url: '/category',
    method: 'delete',
    params: { ids }
  })
}

// const queryCategoryById = (id) => {
//   return $axios({
//     url: `/category/${id}`,
//     method: 'get'
//   })
// }