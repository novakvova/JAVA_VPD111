import {Button, Col, Pagination, Row} from "antd";
import {Link, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {ICategoryItem, ICategorySearch, IGetCategories} from "./types.ts";
import http_common from "../../http_common.ts";
import CategoryCard from "./CategoryCard.tsx";

const CategoryListPage = () => {

    const [body, setBody] = useState<IGetCategories>({
        content: [], //записи відсутні
        totalElements: 0 //загальні кількість елементів 0
    });

    const [searchParams, setSearchParams] = useSearchParams();

    const [search, setSearch] = useState<ICategorySearch>({
        name: searchParams.get("name") || "",
        page: Number(searchParams.get("page")) || 1,
        size: Number(searchParams.get("size")) || 2
    });


    useEffect(() => {
        http_common.get<IGetCategories>("/api/categories/search",
            {
                params: {
                    ...search,
                    page: search.page-1
                }
            })
            .then(resp => {
               const {data} = resp;
                console.log("Server data", data);
                setBody(data);
                //setList(data);
            });
    },[search]);

    const {content, totalElements} = body;

    const handleDelete = async (id: number) => {
        try {
            await http_common.delete(`/api/categories/${id}`);
            setBody({...body, content: content.filter(x=>x.id!=id)});
        }
        catch(error) {
            console.log("Error delete", error);
        }
    }

    const handlePageChange = async (page: number, newPageSize: number) => {
        findCategories({...search, page, size: newPageSize});
    };

    const findCategories = (model: ICategorySearch) => {
        setSearch(model);
        updateSearchParams(model);
    }

    const updateSearchParams = (params : ICategorySearch) =>{
        for (const [key, value] of Object.entries(params)) {
            if (value !== undefined && value !== 0 && value!="") {
                searchParams.set(key, value);
            } else {
                searchParams.delete(key);
            }
        }
        setSearchParams(searchParams);
    };


    return (
        <>
            <h1>Список категорій</h1>
            <Link to={"/category/create"}>
                <Button type="primary" size={"large"}>
                    Додати
                </Button>
            </Link>

            <Row gutter={16}>
                <Col span={24}>
                    <Row>
                        {content.length === 0 ? (
                            <h2>Список пустий</h2>
                        ) : (
                            content.map((item) =>
                                <CategoryCard key={item.id} item={item} handleDelete={handleDelete} />,
                            )
                        )}
                    </Row>
                </Col>
            </Row>

            <Row style={{width: '100%', display: 'flex', marginTop: '25px', justifyContent: 'center'}}>
                <Pagination
                    showTotal={(total, range) => {
                        // console.log("range ", range);
                        return (`${range[0]}-${range[1]} із ${total} записів`);
                    }}
                    current={search.page}
                    defaultPageSize={search.size}
                    total={totalElements}
                    onChange={handlePageChange}
                    pageSizeOptions={[1, 2, 5, 10]}
                    showSizeChanger
                />
            </Row>
        </>
    )
}

export default CategoryListPage;