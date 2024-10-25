package com.api.domain.repository;


import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface QuantityApplicationsRepository extends JpaRepository<FactHiring, Long> {

    //    use localhost305;
//
//
//    WITH ranked_data AS (
//            SELECT
//                    dd.year,
//            dd.month,
//            SUM(fct.number_of_applications) AS applications,
//    DENSE_RANK() OVER (ORDER BY dd.year, dd.month) AS application_rank,
//    DATE(CONCAT(dd.year, '-', dd.month, '-01')) AS month_date
//    FROM
//    fact_applications fct
//    LEFT JOIN
//    dim_date dd ON dd.date_id = fct.date_id
//    GROUP BY
//    dd.year, dd.month
//),
//    current_data AS (
//    -- Seleciona os últimos 3 meses de dados históricos
//            SELECT
//            rd.year,
//    rd.month,
//    rd.applications,
//    ROW_NUMBER() OVER (ORDER BY rd.year DESC, rd.month DESC) AS row_num
//    FROM
//    ranked_data rd
//    WHERE
//    rd.month_date <= NOW() -- Considera apenas meses até o mês atual
//),
//    forecast_data AS (
//    -- Gera previsões para os próximos três meses
//            SELECT
//            (SELECT applications FROM current_data WHERE row_num = 3) AS app_3_months_ago,
//        (SELECT applications FROM current_data WHERE row_num = 2) AS app_2_months_ago,
//        (SELECT applications FROM current_data WHERE row_num = 1) AS app_last_month,
//        (SELECT year FROM current_data WHERE row_num = 1) AS last_year,
//        (SELECT month FROM current_data WHERE row_num = 1) AS last_month
//),
//    predictions AS (
//    -- Gera as previsões com os meses futuros
//            SELECT
//            -- Previsão para o próximo mês baseada nos últimos 3 meses
//            (fd.app_3_months_ago + fd.app_2_months_ago + fd.app_last_month) / 3.0 AS predicted_next_month_1,
//        (fd.app_2_months_ago + fd.app_last_month) / 2.0 AS predicted_next_month_2,
//    fd.app_last_month AS predicted_next_month_3,
//    fd.last_year,
//    fd.last_month
//            FROM
//    forecast_data fd
//),
//    future_months AS (
//    -- Define os próximos meses e anos dinamicamente
//            SELECT
//            -- Primeiro mês previsto
//            CASE
//            WHEN p.last_month = 12 THEN p.last_year + 1 ELSE p.last_year
//            END AS year_1,
//    CASE
//            WHEN p.last_month = 12 THEN 1 ELSE p.last_month + 1
//            END AS month_1,
//        1 AS rank_1,
//    p.predicted_next_month_1 AS applications_predicted_1,
//
//        -- Segundo mês previsto
//            CASE
//            WHEN p.last_month = 11 THEN p.last_year + 1 WHEN p.last_month = 12 THEN p.last_year + 1 ELSE p.last_year
//            END AS year_2,
//    CASE
//            WHEN p.last_month = 11 THEN 1 WHEN p.last_month = 12 THEN 2 ELSE p.last_month + 2
//            END AS month_2,
//        2 AS rank_2,
//    p.predicted_next_month_2 AS applications_predicted_2,
//
//        -- Terceiro mês previsto
//            CASE
//            WHEN p.last_month = 10 THEN p.last_year + 1 WHEN p.last_month = 11 THEN p.last_year + 1 WHEN p.last_month = 12 THEN p.last_year + 1 ELSE p.last_year
//            END AS year_3,
//    CASE
//            WHEN p.last_month = 10 THEN 1 WHEN p.last_month = 11 THEN 2 WHEN p.last_month = 12 THEN 3 ELSE p.last_month + 3
//            END AS month_3,
//        3 AS rank_3,
//    p.predicted_next_month_3 AS applications_predicted_3
//            FROM
//            predictions p
//    )
//-- Resultado final: traz os meses e anos das previsões junto com os valores e o rank
//    SELECT
//    CONCAT(fm.year_1, '-', LPAD(fm.month_1, 2, '0')) AS predicted_month_year,
//    fm.applications_predicted_1 AS applications_predicted,
//    fm.rank_1 AS `rank`
//    FROM
//    future_months fm
//    UNION ALL
//    SELECT
//    CONCAT(fm.year_2, '-', LPAD(fm.month_2, 2, '0')) AS predicted_month_year,
//    fm.applications_predicted_2 AS applications_predicted,
//    fm.rank_2 AS `rank`
//    FROM
//    future_months fm
//    UNION ALL
//    SELECT
//    CONCAT(fm.year_3, '-', LPAD(fm.month_3, 2, '0')) AS predicted_month_year,
//    fm.applications_predicted_3 AS applications_predicted,
//    fm.rank_3 AS `rank`
//    FROM
//    future_months fm
//    ORDER BY
//    predicted_month_year;


//    select dd.year, dd.month, sum(fct.number_of_applications) as applicattions,dense_rank() over( order by dd.year,dd.month) as "rank"
//    from
//    fact_applications fct
//    left join dim_date dd
//    on dd.date_id = fct.date_id
//    group by 1,2;

    @Query(value = """
        SELECT 
            MONTH(fh.hiring_date) AS month,
            YEAR(fh.hiring_date) AS year,
            SUM(fh.qty_hirings) AS total_hirings
        FROM 
            fact_hirings fh
        WHERE 
            fh.hiring_date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH) AND CURRENT_DATE
        GROUP BY 
            MONTH(fh.hiring_date), YEAR(fh.hiring_date)
        ORDER BY 
            YEAR(fh.hiring_date), MONTH(fh.hiring_date)
    """, nativeQuery = true)
    List<Object[]> findAllHiringDatesAndTotalHiringsByMonths(@Param("months") int months);


}

