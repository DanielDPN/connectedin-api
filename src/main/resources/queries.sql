-- Trigger after_update on invitations
CREATE TRIGGER after_update
  AFTER UPDATE
  ON invitations FOR EACH ROW
BEGIN
  IF NEW.status = 1 THEN
    INSERT INTO users_contacts (user_id, contact_id) VALUES (NEW.sender_id, NEW.receiver_id);
    INSERT INTO users_contacts (user_id, contact_id) VALUES (NEW.receiver_id, NEW.sender_id);
  END IF;
END;
