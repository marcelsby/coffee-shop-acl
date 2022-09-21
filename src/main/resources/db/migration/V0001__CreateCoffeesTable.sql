CREATE TABLE public.coffees
(
    id          uuid PRIMARY KEY,
    name        varchar,
    description varchar,
    value       float,
    created_at  timestamp
);