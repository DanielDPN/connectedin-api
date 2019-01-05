-- Trigger after_update on invitations
CREATE TRIGGER after_update_invitations
  AFTER UPDATE
  ON invitations FOR EACH ROW
BEGIN
  IF NEW.status = 1 THEN
    INSERT INTO users_contacts (user_id, contact_id) VALUES (NEW.sender_id, NEW.receiver_id);
    INSERT INTO users_contacts (user_id, contact_id) VALUES (NEW.receiver_id, NEW.sender_id);
  END IF;
END;

-- Trigger after_insert on posts
CREATE TRIGGER after_insert_posts
  AFTER INSERT
  ON posts FOR EACH ROW
BEGIN
  INSERT INTO users_posts (user_id, post_id) VALUES (NEW.owner_id, NEW.id);
END;
