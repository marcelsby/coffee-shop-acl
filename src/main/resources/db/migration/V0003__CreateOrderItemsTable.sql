CREATE TABLE public.order_items
(
    id         uuid PRIMARY KEY,
    created_at timestamp,
    order_id   uuid,
    coffee_id  uuid,
    value      float,
    amount     int,

    CONSTRAINT fk_coffee FOREIGN KEY (coffee_id) REFERENCES public.coffees (id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES public.orders (id)
)