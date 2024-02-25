import './App.css'
import {Route, Routes} from "react-router-dom";
import DefaultLayout from "./containers/default/DefaultLayout.tsx";
import CategoryListPage from "./category/list/CategoryListPage.tsx";
import CategoryCreatePage from "./category/create/CategoryCreatePage.tsx";
import CategoryEditPage from "./category/edit/CategoryEditPage.tsx";

function App() {

  return (
    <>
        <Routes>
            <Route path={"/"} element={<DefaultLayout/>}>
                <Route index element={<CategoryListPage/>}/>
                <Route path={"category"}>
                    <Route path={"create"} element={<CategoryCreatePage/>}/>
                    <Route path={"edit/:id"} element={<CategoryEditPage/>}/>
                </Route>
            </Route>
        </Routes>
    </>
  )
}

export default App
