sudo mysqldump -u root -pngs@123#lanka --routines --no-data nextgenmed | gzip > /home/nextgenmed/backup/db/nextgenmed_routine_`date +%Y-%m-%d`.sql
sudo mysqldump -u root -pngs@123#lanka nextgenmed | gzip > /home/nextgenmed/backup/db/nextgenmed_`date +%Y-%m-%d`.sql.gz

