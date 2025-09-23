-- SQL Migration to add eventName column to equipment table
-- Run this in your PostgreSQL database (pgAdmin or command line)

-- Add the eventName column to the equipment table
ALTER TABLE equipment
ADD COLUMN event_name VARCHAR(255);

-- Optional: Add index for better search performance
CREATE INDEX idx_equipment_event_name ON equipment(event_name);

-- Optional: Add comment to the column
COMMENT ON COLUMN equipment.event_name IS 'Name of the event associated with this equipment';

-- Verify the column was added (you can run this to check)
-- SELECT column_name, data_type, is_nullable
-- FROM information_schema.columns
-- WHERE table_name = 'equipment' AND column_name = 'event_name';
