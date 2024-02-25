import {Button, Col, Row} from "antd";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {ICategoryItem} from "./types.ts";
import http_common from "../../http_common.ts";
import CategoryCard from "./CategoryCard.tsx";

const CategoryListPage = () => {

    const [list, setList] = useState<ICategoryItem[]>([]);

    useEffect(() => {
        http_common.get<ICategoryItem[]>("/api/categories")
            .then(resp => {
               const {data} = resp;
                console.log("Server data", data);
                setList(data);
            });
    },[]);

    const handleDelete = async (id: number) => {
        try {
            await http_common.delete(`/api/categories/${id}`);
            setList(list.filter(x=>x.id!=id));
        }
        catch(error) {
            console.log("Error delete", error);
        }
    }

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
                        {list.length === 0 ? (
                            <h2>Список пустий</h2>
                        ) : (
                            list.map((item) =>
                                <CategoryCard key={item.id} item={item} handleDelete={handleDelete} />,
                            )
                        )}
                    </Row>
                </Col>
            </Row>
        </>
    )
}

export default CategoryListPage;