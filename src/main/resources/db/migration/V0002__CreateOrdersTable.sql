CREATE TABLE public.orders
(
    id          uuid PRIMARY KEY,
    created_at  timestamp,
    status      varchar,
    customer_id uuid,
    attendant_id   uuid
)