FROM node:18-alpine
WORKDIR /app
COPY . .
RUN yarn install 
ENV VITE_BACKEND_URL http://localhost:8080
EXPOSE 5173

CMD [ "yarn", "run", "dev" ]
