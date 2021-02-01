FROM node:15.7.0-alpine As builder
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.19.6-alpine
COPY --from=builder /usr/src/app/dist/frontend/ /usr/share/nginx/html
